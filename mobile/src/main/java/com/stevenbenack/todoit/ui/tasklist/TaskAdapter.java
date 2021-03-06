package com.stevenbenack.todoit.ui.tasklist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stevenbenack.todoit.R;
import com.stevenbenack.todoit.storage.ToDoTask;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{
    private List<ToDoTask> toDoTasks;
    private final TaskClickListener taskClickListener;

    public interface TaskClickListener {
        void onTaskClick(UUID taskId, int taskPosition);
    }

    public TaskAdapter(List<ToDoTask> toDoTasks, TaskClickListener taskClickListener) {
        this.toDoTasks = toDoTasks;
        this.taskClickListener = taskClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_task_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ToDoTask task = toDoTasks.get(position);
        viewHolder.bind(task);
    }

    @Override
    public int getItemCount() {
        return toDoTasks.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Recycler View ViewHolder
    public final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.list_item_title) TextView listItemTitle;
        @BindView(R.id.list_item_description) TextView listItemDescription;

        private ToDoTask task;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        public void bind(ToDoTask toDoTask) {
            task = toDoTask;
            listItemTitle.setText(task.getTitle());
            listItemDescription.setText(task.getDescription());
        }

        @Override
        public void onClick(View view) {
            taskClickListener.onTaskClick(task.getId(), getAdapterPosition());

        }
    }
}

