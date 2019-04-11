package cz.jakubturcovsky.seminar6

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RemoteViews

/**
 * For more info look here: https://medium.com/android-bits/android-widgets-ad3d166458d3
 */
class SimpleWidget : AppWidgetProvider() {

    /**
     * Updated every updatePeriodMillis (attribute)
     */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val views = RemoteViews(context.packageName, R.layout.layout_widget)

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/2uWZSr7"))
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        views.setOnClickPendingIntent(R.id.tvWidget, pendingIntent)

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    /**
     * Instance is created
     */
    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    /**
     * Last widget instance is deleted
     */
    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

    /**
     * Widget is deleted
     */
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    /**
     * Widget is placed or resized
     */
    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }
}