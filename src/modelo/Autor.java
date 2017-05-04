package modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "autor", schema = "public")
@PrimaryKeyJoinColumn(name = "id") 
public class Autor extends PessoaFisica{


}