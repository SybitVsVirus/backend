package de.wevsvirus.servlet.controller.data;

public class JsonResponse {

    private final boolean success;
    private final Object data;
    private final Object error;

    private JsonResponse(final boolean success, final Object data, final Object error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static JsonResponse success() {
        return new JsonResponse(true, null, null);
    }

    public static JsonResponse withData(final Object data) {
        return new JsonResponse(true, data, null);
    }

    public static JsonResponse failure() {
        return new JsonResponse(false, null, null);
    }

    public static JsonResponse withError(final Object error) {
        return new JsonResponse(false, null, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public Object getError() {
        return error;
    }

}
