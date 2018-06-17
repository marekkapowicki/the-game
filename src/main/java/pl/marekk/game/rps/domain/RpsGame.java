package pl.marekk.game.rps.domain;

class RpsGame extends TwoPlayersGame<RpsInput> {
    RpsGame(RpsInput playerOne, RpsInput playerTwo) {
        super(playerOne, playerTwo);
    }
}
