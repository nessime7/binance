package com.example.binance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Inspiracja to https://github.com/achannarasappa/ticker

Proszę napisać aplikacje wyświetlającą aktualne ceny kryptowalut łączącą się z API Binance:
https://www.binance.com/pl/binance-api
https://binance-docs.github.io/apidocs/spot/en/#websocket-market-streams


Aplikacja ma wystawiać restowe API z przykładowym endpointem: GET /pairs/BTC-USD, wpisując pare,
chce widzieć najnowszą cene jakiejś pary. Idealnie byloby to użyć lokalny aplikacyjny cache do ciągłego
aktualizowania cen, a kiedy uderzamy przez HTTP do aplikacji, cena jest ściągana z lokalnego cache, nie z binance.
Na początek możesz sobie założyć, że obsługujemy jakieś 1,2 najważniejsze pary(BTC-USD, ETH-USD),
potem możemy rozszerzać apke.
 */

@SpringBootApplication
public class BinanceApplication {

    public static void main(String[] args) {
		SpringApplication.run(BinanceApplication.class, args);
    }
}
