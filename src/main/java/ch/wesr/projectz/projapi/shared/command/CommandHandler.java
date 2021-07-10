package ch.wesr.projectz.projapi.shared.command;

@FunctionalInterface
public
interface CommandHandler<C extends Command, R> {
  R handle(C cmd);
}
