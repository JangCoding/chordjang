package com.example.chordjang.Measure;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/measures")
@NoArgsConstructor
public class MeasureController {
    private MeasureService measureService;

    @GetMapping
    public ResponseEntity<List<Measure>> getMeasures(@RequestParam Long musicSheetId) {
        List<Measure> measureList = measureService.getMeasures(musicSheetId);
        return ResponseEntity.status(HttpStatus.OK).body(measureList);
    }
}
