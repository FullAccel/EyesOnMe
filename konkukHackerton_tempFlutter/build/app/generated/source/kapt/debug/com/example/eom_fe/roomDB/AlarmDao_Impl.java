package com.example.eom_fe.roomDB;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AlarmDao_Impl implements AlarmDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AlarmDataModel> __insertionAdapterOfAlarmDataModel;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAlarm;

  public AlarmDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAlarmDataModel = new EntityInsertionAdapter<AlarmDataModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `active_alarms` (`serialNum`,`alarm_code`,`time`,`content`,`type`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AlarmDataModel value) {
        stmt.bindLong(1, value.getSerialNum());
        stmt.bindLong(2, value.getAlarm_code());
        if (value.getTime() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTime());
        }
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        stmt.bindLong(5, value.getType());
      }
    };
    this.__preparedStmtOfDeleteAlarm = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM active_alarms WHERE alarm_code = ?";
        return _query;
      }
    };
  }

  @Override
  public void addAlarm(final AlarmDataModel item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAlarmDataModel.insert(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAlarm(final int alarm_code) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAlarm.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, alarm_code);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAlarm.release(_stmt);
    }
  }

  @Override
  public List<AlarmDataModel> getAllAlarms() {
    final String _sql = "select * from active_alarms";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSerialNum = CursorUtil.getColumnIndexOrThrow(_cursor, "serialNum");
      final int _cursorIndexOfAlarmCode = CursorUtil.getColumnIndexOrThrow(_cursor, "alarm_code");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final List<AlarmDataModel> _result = new ArrayList<AlarmDataModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final AlarmDataModel _item;
        final int _tmpSerialNum;
        _tmpSerialNum = _cursor.getInt(_cursorIndexOfSerialNum);
        final int _tmpAlarm_code;
        _tmpAlarm_code = _cursor.getInt(_cursorIndexOfAlarmCode);
        final String _tmpTime;
        if (_cursor.isNull(_cursorIndexOfTime)) {
          _tmpTime = null;
        } else {
          _tmpTime = _cursor.getString(_cursorIndexOfTime);
        }
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        _item = new AlarmDataModel(_tmpSerialNum,_tmpAlarm_code,_tmpTime,_tmpContent,_tmpType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
