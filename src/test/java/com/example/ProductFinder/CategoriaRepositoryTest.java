package com.example.ProductFinder;

import com.example.ProductFinder.modelo.Categoria;
import com.example.ProductFinder.repositorio.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;





@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//para poder interactuar con la base de datos real
@Rollback(false)// para que no me haga retroceder un proceso
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void testCrearCategoria(){
        Categoria categoriaGuardada = categoriaRepository.save(new Categoria("gaseosas"));
        assertThat(categoriaGuardada.getId()).isGreaterThan(0);
    }
}
