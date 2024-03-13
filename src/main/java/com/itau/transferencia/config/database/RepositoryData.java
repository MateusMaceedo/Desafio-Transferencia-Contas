package com.itau.transferencia.config.database;

import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;

public interface RepositoryData {
    void insertItem(String key, TransferAccountDTO dto);
}
