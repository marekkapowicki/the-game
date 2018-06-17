package pl.marekk.game.application;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpsApplication {
    public static void main(String[] args) {

        final Menu menu = Menu.create();
        while (menu.play()) {
        }
    }
}
