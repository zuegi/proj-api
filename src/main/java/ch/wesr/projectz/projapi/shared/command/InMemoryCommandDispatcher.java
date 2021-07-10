package ch.wesr.projectz.projapi.shared.command;

import java.util.HashMap;
import java.util.Map;

class InMemoryCommandDispatcher implements DispatchCommands {

 private final Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> handlers = new HashMap<>();

 @Override
 @SuppressWarnings("unchecked")
 public <C extends Command, R> R dispatch(C cmd) {
   CommandHandler<? extends Command, ?> handler = handlers.get(cmd.getClass());
   return ((CommandHandler<C, R>) handler).handle(cmd);
 }

 <C extends Command, R> void registerHandler(Class commandType, CommandHandler<C, R> handler) {
   handlers.put(commandType, handler);
 }

}
