package com.grabas.gestaoprodutos.modulos.categoria.model;

import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaRequest;
import com.grabas.gestaoprodutos.modulos.produto.model.Produto;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORIA")
public class Categoria {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "CATEGORIA_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUTO_ID"))
    private List<Produto> produtos;

    public static Categoria to(CategoriaRequest request) {
        var categoria = new Categoria();
        BeanUtils.copyProperties(request, categoria);
        return categoria;
    }
}
