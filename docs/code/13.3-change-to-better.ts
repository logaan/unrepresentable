var record: {id:         number,
             deleted:    Date   | null,
             deleted_by: string | null}

type deletion = {deleted:    Date,
                 deleted_by: string};

var record: {id:       number,
             deletion: deletion | null}
