type deletion = {deleted:    Date,
                 deleted_by: string};

var record: {id:       number,
             deletion: deletion | null}
                                                        
record = {id:       1,
          deletion: {deleted:    null
                     deleted_by: "Logan"}}
