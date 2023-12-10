# Aluno
Antonio Pereira da Silva

# Projeto AgenciaCarros


## Arquitetura do aplicativo
Olá Professor desenvolvi o aplicativo AgenciaCarros seguindo as especificações aplicando os recursos ministrados de aula.

***** Nota *****
A ideia inicial era algo um pouco mais elaborado. De um aplicativo em que se pudesse cadastros os veículos para venda,
no caso dos vendedores. E com o recurso para os compradores de acessar o cadastro e dar um lance e colocar uma mensagem.

Exemplo: Tenho interesse, aceita oferta ?

Para isso implementei uma nova tela no my_nav. Criei um MensagemFragment e mais todo fluxo. Em resumo era ter associado
ao cadastro do veículo uma lista de possíveis interessados com os dados:

Nome:
Tel:
Mensagem:

Algo como List<Interessados> associado ao cadastro do veículo.

Mas esse desenho de desenvolvimento deu alguns problemas e com o prazo se aproximando decidi simplicar o modelo, em que
continua aplicando os conceitos da aula. Mas com modelo mais simples com o Crud para o cadastro de Veiculo e com o campo
Chat que a ideia é ser a interface entre os Vendedores que colocam alguma informação do veículo. E para os compradores
poderiam editar esse campo para fazer alguma observação.

Exemplo: Tenho interesse, aceita oferta ?