-- Table Category
CREATE TABLE IF NOT EXISTS public.category (
  id BIGSERIAL NOT NULL,
  title varchar(255) NOT NULL,
  colour varchar(255) NOT NULL,
  created_at timestamp,
  updated_at timestamp,
  deleted_at timestamp,
  PRIMARY KEY(id),
  CONSTRAINT idx_category_title UNIQUE(title)
);