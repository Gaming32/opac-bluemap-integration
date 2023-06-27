package io.github.gaming32.opacbluemapintegration;

import org.quiltmc.qup.json.JsonReader;
import org.quiltmc.qup.json.JsonWriter;

import java.io.IOException;

public class OpacBluemapConfig {
    private int updateInterval = 12000; // Every 10 minutes

    public void read(JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            final String key;
            switch (key = reader.nextName()) {
                case "updateInterval" -> updateInterval = reader.nextInt();
                default -> {
                    OpacBluemapIntegration.LOGGER.warn("Unknown OPaC BlueMap config key {}. Skipping.", key);
                    reader.skipValue();
                }
            }
        }
        reader.endObject();
    }

    public void write(JsonWriter writer) throws IOException {
        writer.beginObject();

        writer.comment("How often, in ticks, the markers should be refreshed. Set to 0 to disable automatic refreshing.");
        writer.comment("Default is 10 minutes (12000 ticks).");
        writer.name("updateInterval").value(updateInterval);

        writer.endObject();
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
    }
}
