package hu.adam.sipos.music.serialization;

public interface SerializationService {
    String serialize(Object object);

    <T> T deserialize(String serialized, Class<T> clazz);
}
