package ch.wesr.projectz.projapi.shared.command;

interface DispatchCommands {
  <C extends Command, R> R dispatch(C cmd);
}
