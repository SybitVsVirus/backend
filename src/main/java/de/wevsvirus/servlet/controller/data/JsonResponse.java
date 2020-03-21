package de.wevsvirus.servlet.controller.data;

public class JsonResponse {
    protected boolean success = false;
    protected Object data = null;
    protected Object error = null;

    public static JsonResponse success() {
        JsonResponse resp = new JsonResponse();
        resp.setSuccess(true);
        return resp;
    }

    public static JsonResponse withData(final Object data) {
        JsonResponse resp = JsonResponse.success();
        resp.setData(data);
        return resp;
    }

    public static JsonResponse failure() {
        JsonResponse resp = new JsonResponse();
        resp.setSuccess(false);
        return resp;
    }

    public static JsonResponse withError(final Object data) {
        JsonResponse resp = JsonResponse.failure();
        resp.setError(data);
        return resp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
