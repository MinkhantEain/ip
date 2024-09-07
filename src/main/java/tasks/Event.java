package tasks;

import java.util.Objects;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean completed) {
        super(description, completed);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String getSaveFormat() {
        return String.format("E | %d | %s | %s to %s", super.intComplete(), super.getDescription(), from, to);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", super.completedStringRepresentation(), super.getDescription(), from, to);
    }

    public static Event load(String input) {
        assert !input.isEmpty();
        String[] parameters = input.split("\\|");
        assert input.length() == 4;
        String[] dateRange = parameters[3].split("to");
        boolean completed = parameters[1].trim().equals("1");
        return new Event(
                parameters[2].trim(),
                dateRange[0].trim(),
                dateRange[1].trim(),
                completed
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Event event = (Event) o;
        return Objects.equals(from, event.from) && Objects.equals(to, event.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), from, to);
    }
}
