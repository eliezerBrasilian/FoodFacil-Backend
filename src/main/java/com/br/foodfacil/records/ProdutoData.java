package com.br.foodfacil.records;

import jakarta.validation.constraints.NotNull;

public record ProdutoData(
        @NotNull String id,
        @NotNull String titulo,
        @NotNull String descricao,
        @NotNull String valor){}
