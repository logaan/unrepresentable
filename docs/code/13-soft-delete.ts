var record: {id:         number,
             deleted:    Date   | null,
             deleted_by: string | null}

// Valid. Not deleted.
record = {id:         1,
          deleted:    null,
          deleted_by: null}

// Valid. Soft deleted.
record = {id:         1,
          deleted:    new Date("2022-03-25"),
          deleted_by: "Logan"}

// Invalid. If it's soft deleted we need both fields set.
record = {id:         1,
          deleted:    null,
          deleted_by: "Logan"}
