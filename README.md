#  Sistema de Concessionária – CRUD Completo (Spring Boot + MySQL + Thymeleaf)

Este projeto é um sistema Web completo para gerenciamento de carros em uma concessionária, com operações de **CRUD**:

- Adicionar carros  
- Listar carros  
- Editar carros  
- Excluir carros  
- Visualização em interface web (Thymeleaf + Bootstrap)
  
---

## 🛠 Tecnologias Utilizadas

- Java 21  
- Spring Boot 3  
- Spring MVC  
- Spring Data JPA  
- MySQL  
- Thymeleaf  
- Bootstrap   
- Maven  

---

## 🗄 Modelo do Banco de Dados

Estrutura completa em:

src/main/resources/db/schema.sql


### **Tabela CARRO**

| Campo | Tipo | Descrição |
|-------|-------|------------|
| id | BIGINT | Identificador (auto increment) |
| modelo | VARCHAR(100) | Modelo do carro |
| marca | VARCHAR(100) | Marca do carro |
| ano | INT | Ano do veículo |
| preco | DECIMAL(10,2) | Preço |

  
    

#### Sistema desenvolvido por Skollymowskyv S. Alcântara
- Projeto criado para implementação de conhecimento e aperfeiçoamento da prática.  

