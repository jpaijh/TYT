package com.qskj.tyt.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Post带map参数请求-适用条件（服务器返回：JsonObject）
 *
 * 使用例子：天易通 CB_WarehousingDetailsActivity_JYDW 中查看 预录入单
 * Created by 赵 鑫 on 2015/12/31.
 */
public class JsonObjectPostRequest extends Request<JSONObject> {

    private Map<String, String> mMap;
    private Listener<JSONObject> mListener;

    public JsonObjectPostRequest(String url, Listener<JSONObject> listener, ErrorListener errorListener, Map map) {
        super(Method.POST, url, errorListener);
        mListener = listener;
        mMap = map;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mMap;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }

}
