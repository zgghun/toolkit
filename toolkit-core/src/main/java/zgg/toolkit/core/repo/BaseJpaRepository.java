package zgg.toolkit.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by zgg on 2018/09/12
 */
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
