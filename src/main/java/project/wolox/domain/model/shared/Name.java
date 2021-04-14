package project.wolox.domain.model.shared;

import project.wolox.domain.exception.Validate;
import reactor.core.publisher.Mono;

public class Name {
    private String name;

    public Name(){}

    private Name(String name) {
        this.name = name;
    }

    public static Mono<Name> create(String name){
        Name name1 = new Name(name);
        return name1.validate().then(Mono.just(name1));
    }

    public Mono<Void> validate(){
        return Validate
                .nullEntityValidate(name, "name");
    }
}
