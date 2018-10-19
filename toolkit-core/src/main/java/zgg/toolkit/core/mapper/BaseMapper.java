package zgg.toolkit.core.mapper;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by zgg on 2018/10/18
 */
@Component
@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>{
}
