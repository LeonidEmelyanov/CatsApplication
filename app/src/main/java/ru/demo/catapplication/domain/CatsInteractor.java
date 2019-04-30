package ru.demo.catapplication.domain;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
import ru.demo.catapplication.data.CatModel;
import ru.demo.catapplication.data.CatType;

public class CatsInteractor {

    public Single<List<CatModel>> getCats() {
        return Single.fromCallable(() -> {
            Thread.sleep(1000);

            return Arrays.asList(
                    new CatModel(CatType.PUSHEEN,
                            "Pusheen",
                            "This is Pusheen, most popular cat in the world!"),
                    new CatModel(CatType.UNICORN,
                            "Unicorn",
                            "This is mythical animal with rainbow hear, flying in the wind..."),
                    new CatModel(CatType.PIXELS,
                            "8-bit Cat",
                            "Rare cat from the history. The origin is lost in the ages.")/*,
                    new CatModel(CatType.FIRE_CAT,
                            "Fire Cat",
                            "Fire!!!")*/);
        });
    }
}
