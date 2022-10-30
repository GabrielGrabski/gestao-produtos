package com.grabas.gestaoprodutos.modulos.produto.model;

import com.grabas.gestaoprodutos.comum.enums.EStatus;
import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import com.grabas.gestaoprodutos.modulos.produto.dto.ProdutoRequest;
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
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
                private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @ManyToMany(mappedBy = "produtos")
    private List<Categoria> categorias;

    public static Produto to(ProdutoRequest request, List<Categoria> categorias) {
        var produto = new Produto();
        BeanUtils.copyProperties(request, produto);
        produto.setCategorias(categorias);
        return produto;
    }
}
