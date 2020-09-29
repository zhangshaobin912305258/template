package com.zhang.template.converter.nav;

import com.zhang.template.converter.BaseConverter;
import com.zhang.template.entity.Navigation;
import com.zhang.template.vo.nav.NavVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NavConverter extends BaseConverter<NavVo, Navigation> {
}
