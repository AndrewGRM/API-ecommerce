package br.com.ramos.ecommerce.database.model;

public enum Role {

    ADMIN("Administrador"),
    USER("Usuário Comum");

    private final String descricao;

    Role(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
