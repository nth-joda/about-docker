package com.example.learnspring.Requests;

import com.example.learnspring.Enums.AlgorithmName;
import lombok.Data;

import java.util.List;

@Data
public class SortRequest {
    private AlgorithmName sortName;
    private List<Integer> unsortedList;
}
