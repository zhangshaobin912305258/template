package com.zhang.template.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
    boolean has = metaObject.hasGetter("createTime");
    if (has) {
      Object createTime = getFieldValByName("createTime", metaObject);
      if (createTime == null) {
        strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
      }
    }
  }
}
