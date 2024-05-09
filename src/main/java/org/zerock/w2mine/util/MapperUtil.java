package org.zerock.w2mine.util;


import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration() // ModelMapper 설정 변경.
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE) //private로 선언된 필드도 접근할 수 있음
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ModelMapper get() {
        return modelMapper;
    }
}
