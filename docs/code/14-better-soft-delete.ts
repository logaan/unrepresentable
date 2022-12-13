type deletion = {deleted:    Date,
                 deleted_by: string};

var record: {id: number, deletion: deletion | null}

// Valid. Not deleted.
record = {id: 1, deletion: null}

// Valid. Soft deleted.
record = {id: 1,
          deletion: {deleted:    new Date("2022-03-25"),
                     deleted_by: "Logan"}}

// Invalid and will be picked up by type checker
record = {id: 1,
          deletion: {deleted:    null
                     deleted_by: "Logan"}}
