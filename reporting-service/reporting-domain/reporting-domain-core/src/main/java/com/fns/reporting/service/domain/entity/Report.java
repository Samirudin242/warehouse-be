package com.fns.reporting.service.domain.entity;

import com.fns.domain.entity.AggregateRoot;
import com.fns.domain.valueobject.ReportId;
import com.fns.reporting.service.domain.exception.ReportingDomainException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Report extends AggregateRoot<ReportId> {

    private String title;
    private String content;
    private String reportDomain;
    private List<String> failureMessages;

    public static final String FAILURE_MESSAGE_DELIMITER = ",";

    private Report(Builder builder) {
        super.setId(builder.reportId);
        this.title = builder.title;
        this.content = builder.content;
        this.failureMessages = builder.failureMessages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void updateTitle(String newTitle) {
        if (newTitle.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = newTitle;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }

    public void addFailureMessages(List<String> newFailureMessages) {
        if (newFailureMessages != null && !newFailureMessages.isEmpty()) {
            if (this.failureMessages == null) {
                this.failureMessages = new ArrayList<>();
            }
            newFailureMessages.stream()
                    .filter(message -> !message.trim().isEmpty())
                    .forEach(this.failureMessages::add);
        }
    }

    // Getters
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public ReportId getReportId() { return getId(); }
    public List<String> getFailureMessages() { return failureMessages; }

    // Builder Class
    public static class Builder {
        private ReportId reportId;
        private String title;
        private String content;
        private List<String> failureMessages;

        public Builder reportId(ReportId reportId) {
            this.reportId = reportId;
            return this;
        }

        public Builder title(String title) {
            if (title.isEmpty()) {
                throw new ReportingDomainException("Title cannot be null or empty.");
            }
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            if (content == null) {
                throw new ReportingDomainException("Content cannot be null.");
            }
            this.content = content;
            return this;
        }


        public Builder failureMessages(List<String> failureMessages) {
            this.failureMessages = failureMessages;
            return this;
        }


        public Report build() {
            if (this.reportId == null) {
                throw new ReportingDomainException("ReportId must be set.");
            }
            if (this.title == null || this.title.isEmpty()) {
                throw new ReportingDomainException("Title must be set.");
            }
            if (this.content == null) {
                throw new ReportingDomainException("Content must be set.");
            }
            return new Report(this);
        }
    }

    // Override equals and hashCode for proper comparison and hashing
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report report)) return false;
        return Objects.equals(getId(), report.getId()) &&
                Objects.equals(title, report.title) &&
                Objects.equals(content, report.content) &&
                Objects.equals(failureMessages, report.failureMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), title, content, failureMessages);
    }
}
