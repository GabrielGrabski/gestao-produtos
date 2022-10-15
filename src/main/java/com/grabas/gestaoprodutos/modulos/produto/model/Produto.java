package com.grabas.gestaoprodutos.modulos.produto.model;

import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
                private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @ManyToMany(mappedBy = "produtos")
    private List<Categoria> categorias;
}
