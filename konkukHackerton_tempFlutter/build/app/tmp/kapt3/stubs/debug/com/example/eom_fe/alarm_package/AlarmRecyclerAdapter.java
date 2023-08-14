package com.example.eom_fe.alarm_package;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u001b\u001cB\u001d\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u001c\u0010\u0017\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR*\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0007\u00a8\u0006\u001d"}, d2 = {"Lcom/example/eom_fe/alarm_package/AlarmRecyclerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/eom_fe/alarm_package/AlarmRecyclerAdapter$ViewHolder;", "items", "Ljava/util/ArrayList;", "Lcom/example/eom_fe/roomDB/AlarmDataModel;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "itemClickListener", "Lcom/example/eom_fe/alarm_package/AlarmRecyclerAdapter$OnItemClickListener;", "getItemClickListener", "()Lcom/example/eom_fe/alarm_package/AlarmRecyclerAdapter$OnItemClickListener;", "setItemClickListener", "(Lcom/example/eom_fe/alarm_package/AlarmRecyclerAdapter$OnItemClickListener;)V", "getItems", "()Ljava/util/ArrayList;", "setItems", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "OnItemClickListener", "ViewHolder", "app_debug"})
public final class AlarmRecyclerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.eom_fe.alarm_package.AlarmRecyclerAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<com.example.eom_fe.roomDB.AlarmDataModel> items;
    @org.jetbrains.annotations.Nullable
    private com.example.eom_fe.alarm_package.AlarmRecyclerAdapter.OnItemClickListener itemClickListener;
    
    public AlarmRecyclerAdapter(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<com.example.eom_fe.roomDB.AlarmDataModel> items) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<com.example.eom_fe.roomDB.AlarmDataModel> getItems() {
        return null;
    }
    
    public final void setItems(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<com.example.eom_fe.roomDB.AlarmDataModel> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.eom_fe.alarm_package.AlarmRecyclerAdapter.OnItemClickListener getItemClickListener() {
        return null;
    }
    
    public final void setItemClickListener(@org.jetbrains.annotations.Nullable
    com.example.eom_fe.alarm_package.AlarmRecyclerAdapter.OnItemClickListener p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.example.eom_fe.alarm_package.AlarmRecyclerAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.eom_fe.alarm_package.AlarmRecyclerAdapter.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/example/eom_fe/alarm_package/AlarmRecyclerAdapter$OnItemClickListener;", "", "OnItemClick", "", "position", "", "item", "Lcom/example/eom_fe/roomDB/AlarmDataModel;", "app_debug"})
    public static abstract interface OnItemClickListener {
        
        public abstract void OnItemClick(int position, @org.jetbrains.annotations.NotNull
        com.example.eom_fe.roomDB.AlarmDataModel item);
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/eom_fe/alarm_package/AlarmRecyclerAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/eom_fe/databinding/RowBinding;", "(Lcom/example/eom_fe/alarm_package/AlarmRecyclerAdapter;Lcom/example/eom_fe/databinding/RowBinding;)V", "getBinding", "()Lcom/example/eom_fe/databinding/RowBinding;", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.eom_fe.databinding.RowBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        com.example.eom_fe.databinding.RowBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.eom_fe.databinding.RowBinding getBinding() {
            return null;
        }
    }
}