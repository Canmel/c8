package com.camel.c8.service;

import java.util.List;
import java.util.Map;

public interface ISelectList {
    List<? extends Map<String, ?>> select();
}
