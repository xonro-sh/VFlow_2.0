package com.xonro.vflow.dataview.bean;

import com.sun.xml.internal.ws.developer.Serialization;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 报表主题配置表
 * @author Alex
 * @date 2018/2/11
 */
@Entity
@Table(name = "b_xr_dataview_theme")
public class DataViewTheme implements Serializable{
    /**
     * 唯一标识
     */
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    /**
     * 主题名称
     */
    private String themeName;

    /**
     * 主题样式
     */
    private String theme;

    /**
     * 是否有效
     */
    private boolean isActive;

    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "theme_name")
    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    @Column(name = "theme")
    @Length(max = 20000)
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Column(name = "is_active")
    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
