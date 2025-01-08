package io.github.naughtytom001.sesame.model.task.antFarm;

import io.github.naughtytom001.sesame.hook.ApplicationHook;

/**
 * @author Constanline
 * @since 2023/08/04
 */
public class DadaDailyRpcCall {

    public static String home(String activityId) {
        return ApplicationHook.requestString("com.alipay.reading.game.dadaDaily.home",
                "[{\"activityId\":" + activityId + ",\"dadaVersion\":\"1.3.0\",\"version\":1}]");
    }

    public static String submit(String activityId, String answer, Long questionId) {
        return ApplicationHook.requestString("com.alipay.reading.game.dadaDaily.submit",
                "[{\"activityId\":" + activityId + ",\"answer\":\"" + answer + "\",\"dadaVersion\":\"1.3.0\",\"questionId\":" +
                        questionId + ",\"version\":1}]");
    }
}
