package com.kingsoft.lcgl.business.api.journal.mapper;

import com.kingsoft.lcgl.business.api.journal.dto.JournalDto;
import com.kingsoft.lcgl.core.mybatis.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Mapper
public interface JournalMapper {

    void addJournal(@Param("userId") Long userId, @Param("projectId") Long projectId,@Param("journalType") int journalType,@Param("remark")String remark,
                    @Param("content") String content,@Param("taskId") Long taskId,@Param("projectType") String projectType);

    List<JournalDto> getDynamicNow();

    List<JournalDto> getJournalByProjectId(@Param("projectId") Long projectId);

    List<JournalDto> getJournal(@Param("startDate") Long startTime, @Param("endDate") Long endTime, @Param("projectType") String projectType,@Param("content")  String content);
}
