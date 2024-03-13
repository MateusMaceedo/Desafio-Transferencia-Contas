package com.itau.transferencia.adapters.inbound.mapper;

public interface EntityMapper<T, D> {
    D toDomain(T entity);
    T toEntity(D domain);
}
