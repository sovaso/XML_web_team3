import { CommentDTO } from './Comment.dto';
import { GradeDTO } from './Grade.dto';

export interface ReviewDTO{

    comments : Array<CommentDTO>;
    grades : GradeDTO;
    scientificWorkId : string;
    summaryComment : string;
    reviewedId : string;
}
