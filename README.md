# Orange Talents ZUP 


> A linguagem utilizada foi Java v11. As tecnologias usadas do Spring foram Spring Data JPA, Spring Cloud Open Feign, Spring WEB MVC e Spring Web Services. Para criação dos endpoints utilizei o padrão REST. 

> O Spring JPA tem como benefício a facilidade de criar os repositórios, pois dá suporte à criação estendendo interfaces do Spring Data. Pois isso nos deixa livre de ter que implementar a interface referente aos repositórios. No Repository ele tem como objetivo diminuir a quantidade de código para adicionar a camada de acesso e persistência dos dados. Além de já trazer uma pré-implementação de algumas funcionalidades. O JPA trás o método CRUD, juntamente com ordenação e paginação. Podemos criar as interfaces de repositório incluindo métodos de localização que nos mesmos criamos e o Spring vai fornecer a implementação automaticamente. Para a implementação do código trás uma praticidade, pois como programadora não é preciso ficar criando classes concretas para cada repositório, basta criar a interface específica e puxar em cada classe de entidade a interface JpaRepository, além dos métodos CRUD's que vão ser implementados; tem como reconhecimento da interface como um bean do Spring, o que é útil para a injeção de dependência. 

> O Spring Web Services é um produto utilizado para criar serviços na Web de forma orientado a documentos. Ele facilita o desenvolvimento de serviços SOAP, pois permite a criação de forma flexível usando muitas formas de trabalhar cargas úteis XML. Como é um produto que foi criado com base no próprio SPring, você pode usar os conceitos do Spring, como injeção de dependência, sendo parte integrante de seu serviço da Web.
Na implementação do código ele trás a melhor prática como uma prática fácil. Por exemplo, o perfil básico WS-I, onde fica mais fácil ter o acoplamento fraco entre o contrato e a implementação.
O poderoso mapeamento também é característica do Web Service, você pode espalhar a solicitação XML de entrada para todos os objetos, dependendo da mensagem, do cabeçalho de ação SOAP ou de uma expressão XPATH.
Além de que o suporte API XML, as mensagem podem ser tratadas em APIs JAXP padrão, como DOM, SAX e StAX ou até mesmo com tecnologias de empatocamento.
A experiência com Spring-WS permite que os desenvolvedores se atualizem mais rapidamente, pois ele usa os contextos de aplicativos Spring para todas as configurações e são semelhantes ao Spring-MVC.
O Spring Web Services foi construído por Maven, isso ajuda a reutilização do Web Services em seus próprios projetos baseados em Maven.

> No Spring MVC é possível encontrar facilidade de flexibilidade para as requisições web. 
A maioria das aplicações web e os aplicativos móveis (para Androiod e iOS) precisam de uma API RESTful para consumir. Portanto o MVC é um framework que trás grandes vantagens para todos os programadores que o utilizam. Com ele podemos criar aplicações web robustas e flexíveis, também já tem todas as funcionalidades que é necessário para atender as requisições HTTP, distribuir responsabilidade de processamento de dados para outros componentes e preparar a resposta a ser dada. 
MVC significa Model, View e Controller. É importante entender qual é o papel de cada um deles no sistema, pois vai ajudar a trabalhar de forma mais organizada e trazer fácil manutenção. O navegador envia uma requisição HTTP e quem recebe é o MVC, então o controlador busca quem é(classe) responsável para tratar a requisição e entrega os dados enviados pelo navegador, a classe recebe o nome de controller. Então, o controller passa os dados para o model, que vai executar todas as regras de negócio(cálculos, validações e acesso ao banco de dados). Quando o model termina as realizações, elas são devolvidas ao controller, e ele por sua vez retorna o nome da view, junto com as informações que ela precisa para fazer a renderização da página. O framework se encontra com a view que faz o processamento de dados e transforma o resultado em um HTML. E, finalmente o HTML é enviado ao navegador de quem está solicitando.

> O Feign é um cliente REST HTTP que foi desenvolvido pela Netflix para aplicativos Spring Boot. Algo muito interessante no Feign é que não tem necessidade de escrever nenhum código para chamar o serviço, a não ser uma definição de interface. No projeto eu utilizei ele para fazer as requisições na API da tabela FIPE.

> O banco de dados H2 é um banco relacional que foi escrito em Java. Ele pode ser integrado em aplicativos Java ou executo no modo cliente-servidor. 
Todos os modos contam com suporte para bancos de dados persistentes e na memória, e ele também não tem limite de bancos abertos simultaneamente ou conexões abertas. Eu utilizei o modo integrado (local), pois assim que o Java inicia a aplicação, o banco de dados starta junto. 

> Hibernate é uma ferramenta ORM de código aberto e é liderança no mercado e foi inspirado para a especificação do JPA. Ele nasceu sem JPA mas hoje em dia é comum acessar pelo JPA. E também tem várias implementações. 
O Hibernate dispersa o seu código SQL e toda camada JDBC e o SQL vai ser gerada em tempo de execução. Além disso, vai ser gerado um SQL que serve para um banco de dados determinado, pois cada banco tem sua "linguagem". Sendo assim, tem a possibilidade de trocar de banco sem ter que alterar o código Java, pois isso fica sendo responsabilidade da ferramenta.

> API REST é usado para desenvolver  API's que fazem a comunicação entre o cliente e o servidor. Quando o usuário clica em um link e faz uma requisição ele obtém parte dos dados daquela URL especificada. Ele também é baseado em HTTP e tem alta adaptabilidade, além de ter muitas medidas de segurança que são as camadas de arquitetura integrada e consumir apenas largura de banda mínima.

__Logo abaixo, mostrarei o passo a passo do processo para construção do meu código:__ 

    ## Aqui estão todas as classes utilizadas para desenvolver sobre o usuário. ##

```java
@Entity
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @OneToMany (mappedBy = "user")
    @JsonManagedReference
    private Set<VehicleEntity> vehicles;

/**... contrutores, getters and setters**/
```
Acima, demonstra a criação da classe Entity (entidade) User, responsável por representar uma tabela de dados, e cada instância da entidade é uma linha da tabela. A anotação OneToMany informa a quem o One pertence, no caso, os veículos - que podem ser vários -. 

```java @RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable("id") Integer id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity saveUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }
}
```
Acima temos a classe Controller (controlador) User que é responsável por expor um endpoint. Os métodos ficam nessa classe. Ela é responsável por fazer a ligação das duas camadas anteriores, ligando a View ao Modelo e direcionando o fluxo da aplicação. A anotação AutoWired foi utilizada como injeção de dependência; O GetMapping é como se fosse um atalho para o @RequestMapping(method=RequestMethod.GET); o PostMapping foi utilizado para implementar a URL da anotação @RequestMapping com o método de Post; o ResponseStatus foi utilizado para definir o código de status do método HTTP quando foi criado, se deu certo ou não; o RequestBody foi utilizado para salvar na Entidade o que é informado no body de user.

```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
}
```
Acima, utilizei o @Service pois é a classe que fica responsável por toda regra de negócio, execução com chamada de repositório e onde tem todo o código envolvido também.

##Aqui estão todas as classes utilizadas para desenvolver sobre o veículo.##

```java @Entity
@Table(name="vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle")
    private int id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private String year;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "day_rotation")
    private String dayRotation;

    @Column(name = "active_rotation")
    private Boolean activeRotation = false;

    @ManyToOne
    @JoinColumn (name = "id_user", nullable = false)
    @JsonBackReference
    private UserEntity user;

    public VehicleEntity() {
}
```
Acima utilizei o GeneratedValue para gerar um ID que melhor correspondesse ao bando de dados utilizado; A anotação ManyToOne utilizei para listar todos os veículos para cada único usuário; O JoinColumn foi utilizado para marcar a coluna de junção com a entidade. O JsonBackReference foi utilizado para nos trazer a serielização dos dados. 


```java @RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleEntity saveVehicle(@RequestBody VehicleEntity vehicle, @PathVariable("id") Integer id) throws BrandNotFoundException,
                ModelNotFoundException, VehicleYearNotFoundException {
        return vehicleService.saveVehicle(vehicle, id);
    }
}
```
Acima utilizei o @PathVariable para eu conseguir manipular mais de uma variável para vários caminhos na mesma URL.

```java @Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private FipeAPIFeign fipeAPIFeign;


    public VehicleEntity saveVehicle(VehicleEntity vehicle, Integer id) throws BrandNotFoundException, ModelNotFoundException, VehicleYearNotFoundException {
        Brand brandFipe = fipeAPIFeign.getBrands()
                .stream()
                .filter(bf -> bf.getNome().equalsIgnoreCase(vehicle.getBrand()))
                .findFirst()
                .orElseThrow(BrandNotFoundException::new);

        String idMarca = brandFipe.getCodigo();

        Models.Model model = fipeAPIFeign.getModels(idMarca).getModelos()
                .stream()
                .filter(m -> m.getNome().equalsIgnoreCase(vehicle.getModel()))
                .findFirst()
                .orElseThrow(ModelNotFoundException::new);

        Integer idModelo = model.getCodigo();

        Vehicle vehicleYear = fipeAPIFeign.getYears(idMarca, idModelo)
                .stream()
                .filter(v -> v.getCodigo().equals(vehicle.getYear()))
                .findFirst()
                .orElseThrow(VehicleYearNotFoundException::new);

        String ano = vehicleYear.getCodigo();

        VehicleDetails vehicleDetails = fipeAPIFeign.getVehicleDetails(idMarca, idModelo, ano);

        vehicle.setValue(vehicleDetails.getValor());
        setVehicleRotationInfo(vehicle);

        UserEntity user = new UserEntity();
        user.setId(id);

        vehicle.setUser(user);
        return vehicleRepository.save(vehicle);
    }

    private void setVehicleRotationInfo(VehicleEntity vehicle) {
        String dayWeek = "";
        String lastDigitYear = vehicle.getYear().substring(vehicle.getYear().length() - 1);

        switch (lastDigitYear) {
            case "0" :
            case "1":
                dayWeek = "segunda-feira";
                break;

            case "2" :
            case "3" :
                dayWeek = "terça-feira";
                break;

            case "4" :
            case "5" :
                dayWeek = "quarta-feira";
                break;

            case "6" :
            case "7" :
                dayWeek = "quinta-feira";
                break;

            case "8" :
            case "9" :
                dayWeek = "sexta-feira";
                break;
        }

        vehicle.setDayRotation(dayWeek);

        if(dayWeek.equals(Utils.getDayWeek())){
            vehicle.setActiveRotation(true);
        }
    }
}
```
Acima utilizei o Feign para fazer a requisição diretamente da API da FIPE.

Na primeira chamada eu fiz a solicitação e filtrando o nome da marca e setando na função, dizendo para ignorar letras maiúsculas e minúsculas e assim que encontrasse, parasse de buscar, e caso não encontrasse, apresentar uma mensagem amigável de "Marca não encontrada". 

Após receber os dados, guardei em uma String idMarca.

Na segunda chamada fiz o pedido dos modelos, de acordo com tal marca, filtrando o código e modelo e setando a informação com o ano. Fiz a função parar assim que encontrasse o primeiro que foi buscado, e caso não encontrasse, apresentar uma mensagem amigável de modelo não encontrado.

Após receber os dados, salvei em uma String ano.

Na terceira chamada, busquei o veículo utilizando as outras chamadas com os dados salvos e setei o valor de acordo com as características de marca, modelo e ano.

Após terminar essa parte, iniciei o dia do rodízio utilizando o último dígito do ano, com o switch, o número final do ano recebe a informação do dia da semana. Iniciando sempre como se ele não estivesse disponível para rodízio, pois caso estiver, vai receber passar com true.

```java public class Utils {

    public static String getDayWeek(){
        return new DateFormatSymbols().getWeekdays()[Calendar.getInstance().get(Calendar.DAY_OF_WEEK)];
    }
}
```
Acima, criei uma classe Utils (utilitária) para ser usada em qualquer lugar do projeto. O método retorna o dia da semana pra ser utilizado na função do VehicleService, fazendo a comparação do dia de rodízio do veículo.

> Abaixo tem a API funcionando e retornando os dados e os status : 

![alt text](https://github.com/lauraksp/orange-talents-zup/blob/develop/images/teste1.jpeg?raw=true)

![alt text](https://github.com/lauraksp/orange-talents-zup/blob/develop/images/teste2.jpeg?raw=true)

![alt text](https://github.com/lauraksp/orange-talents-zup/blob/develop/images/teste3.jpeg?raw=true)

![alt text](https://github.com/lauraksp/orange-talents-zup/blob/develop/images/teste4.jpeg?raw=true)

Link <https://github.com/lauraksp/orange-talents-zup/tree/develop>
