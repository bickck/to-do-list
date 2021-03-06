package com.todo.list.entity;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.entity.base.UserTimeStamp;

@Entity(name = "USER_TODO_ENTITY")
public class TodoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserEntity user;

	@Column(name = "TITLE")
	private String title;

	@Lob
	@Column(name = "CONTENT")
	private String content;

	@Column(name = "RECOMMAND")
	private Long recommand;

	private String tag;

	@Column(name = "ISAVAILABLEPUBLISH")
	@Enumerated(value = EnumType.STRING)
	private Publish isPublish;

//	@Embedded
//	private UserTimeStamp stamp;
	@CreationTimestamp
	@Column(name = "CREATE_DATE")
	private Timestamp createDate;

	@UpdateTimestamp
	@Column(name = "LAST_UPDATE")
	private Timestamp lastUpdate;

	public TodoEntity() {
		// TODO Auto-generated constructor stub
	}

	public TodoEntity(@NotNull UserEntity user, @NotNull String title, @NotNull String content) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
	}

	public TodoEntity(@NotNull UserEntity user, @NotNull String title, @NotNull String content,
			@NotNull Publish publish) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
		this.isPublish = publish;
	}

	public TodoEntity(@NotNull UserEntity user, @NotNull String title, @NotNull String content, @NotNull Long recommand,
			@NotNull Publish isPublish) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
		this.recommand = recommand;
		this.isPublish = isPublish;
	}

	@Override
	public String toString() {
		return "TodoEntity [id=" + id + ", user=" + user + ", title=" + title + ", content=" + content + ", recommand="
				+ recommand + ", tag=" + tag + ", isPublish=" + isPublish + ", createDate=" + createDate
				+ ", lastUpdate=" + lastUpdate + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Publish getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Publish isPublish) {
		this.isPublish = isPublish;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getRecommand() {
		return recommand;
	}

	public void setRecommand(Long recommand) {
		this.recommand = recommand;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
