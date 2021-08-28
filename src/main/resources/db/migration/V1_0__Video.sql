-- Table Video
CREATE TABLE IF NOT EXISTS public.video (
  id BIGSERIAL NOT NULL,
  title varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  url varchar(255) NOT NULL,
  created_at timestamp,
  updated_at timestamp,
  deleted_at timestamp,
  PRIMARY KEY(id),
  CONSTRAINT idx_video_title UNIQUE(title)
);