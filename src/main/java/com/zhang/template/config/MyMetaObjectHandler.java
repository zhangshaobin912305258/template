package com.zhang.template.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 统一处理创建时间和修改时间
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    boolean has = metaObject.hasGetter("createTime");
    if (has) {
      Object createTime = getFieldValByName("createTime", metaObject);
      if (createTime == null) {
        strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
      }
    }
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    boolean has = metaObject.hasGetter("updateTime");
    if (has) {
      Object updateTime = getFieldValByName("updateTime", metaObject);
      if (updateTime == null) {
        strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
      }
    }
  }
}
