package pl.marekk.game.rps.domain;

import java.util.function.Supplier;

public interface RoundCommandSupplier extends Supplier<CreateRoundCommand> {
}
