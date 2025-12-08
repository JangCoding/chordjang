package com.example.chordjang.Measure;

import java.util.List;

public interface MeasureService {
    List<Measure> getMeasures(Long musicSheetId);
}
