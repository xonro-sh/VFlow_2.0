package com.xonro.vflow.dataview.dao;

import com.xonro.vflow.dataview.bean.DataView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex
 * @date 2018/2/11
 */
@Repository
public interface DataViewRepository extends JpaRepository<DataView, Long> {
}
