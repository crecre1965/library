package fr.training.spring.library.exposition.common;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractAdapter<T,S> {
    /**
     * Map an entity to a Dto
     *
     * @param entity entity
     * @return the mapped dto
     */
    public abstract T mapToDto(S entity);

    /**
     * Map a Dto to an entity
     *
     * @param dto dto
     * @return the mapped entity
     */
    public abstract S mapToEntity(T dto);

    /**
     * Map an entity list to a Dto list
     *
     * @param entityList entityList
     * @return a List of the mapped entity
     */
    public List<T> mapToDtoList(final List<S> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Map a Dto list to an entity list
     *
     * @param dtoList dtoList
     * @return a List of the mapped entity
     */
    public List<S> mapToEntityList(final List<T> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::mapToEntity).collect(Collectors.toList());
    }

}
