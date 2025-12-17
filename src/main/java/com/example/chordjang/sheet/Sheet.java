package com.example.chordjang.sheet;

import com.example.chordjang.bar.DTO.BarReqDTO;
import com.example.chordjang.bar.Bar;
import com.example.chordjang.post.Post;
import com.example.chordjang.chord.RootNote;
import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetNotFoundException;
import com.example.chordjang.sheet.DTO.SheetReqDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Getter
@Table(name = "sheets")
@NoArgsConstructor
public class Sheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote songKey;
    Integer capo;
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true) // Cascade : 하위 항목들도 함께 저장
    @OrderColumn(name = "bar_no") // @OneToMany 관계에서 리스트의 순서를 데이터베이스에 저장하고 관리하기 위한 기능
    List<Bar> bars = new ArrayList<>(); // 마디
    @OneToOne
    @JoinColumn(name = "post_id")
    Post post;

    @Builder
    public Sheet(String title, String singer, String writer, Integer bpm, RootNote songKey, Integer capo, List<Bar> barList) {
        this.title = title;
        this.singer = singer;
        this.writer = writer;
        this.bpm = bpm;
        this.songKey = songKey;
        this.capo = capo;
        this.bars = barList;
    }

    public static Sheet from(SheetReqDTO req) {
        Sheet sheet = new Sheet();
        sheet.update(req);
        return sheet;
    }

    public void update(SheetReqDTO req) {
        if (req.getTitle() != null) this.title = req.getTitle();
        if (req.getSinger() != null) this.singer = req.getSinger();
        if (req.getWriter() != null) this.writer = req.getWriter();
        if (req.getBpm() != null) this.bpm = req.getBpm();
        if (req.getSongKey() != null) this.songKey = req.getSongKey();
        if (req.getCapo() != null) this.capo = req.getCapo();

        Map<Long, Bar> measureMap = this.getBars().stream()
                .filter(bar -> bar.getId() != null)
                .collect(Collectors.toMap(
                        Bar::getId,      // Key
                        Function.identity() // Value 그대로 사용
                ));

        for(int i = 0; i<req.getBarReqDTOList().size(); i++) {
            BarReqDTO barReqDTO = req.getBarReqDTOList().get(i);
            Long measureId = barReqDTO.getId();
            if(measureId != null) {  // 기존 마디 업데이트
                Bar bar = measureMap.get(measureId);
                if(bar == null) {
                    throw new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "Measure", "Id", measureId);
                }
                bar.update(barReqDTO);
                measureMap.remove(measureId);   // 처리 후 Map 에서 지움
            } else {  // 새 마디 생성
                Bar bar = Bar.from(barReqDTO);
                bar.setSheet(this);
                this.addBar(bar);
            }
        }
        this.getBars().removeAll(measureMap.values());
    }

    public void addBar(Bar bar) {
        this.getBars().add(bar);
    }

    public void setPost(Post post) {
        if (post != null) this.post = post;
    }
}
