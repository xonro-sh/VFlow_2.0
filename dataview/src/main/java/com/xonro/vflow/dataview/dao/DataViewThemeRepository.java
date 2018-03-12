package com.xonro.vflow.dataview.dao;

import com.sun.xml.internal.bind.v2.TODO;
import com.xonro.vflow.dataview.bean.DataViewTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex
 * @date 2018/2/11
 */
@Repository
public interface DataViewThemeRepository extends JpaRepository<DataViewTheme, Long> {
    List<DataViewTheme> findByIsActive(boolean isActive);
    // TODO: 2018-3-12 加入缓存
}
